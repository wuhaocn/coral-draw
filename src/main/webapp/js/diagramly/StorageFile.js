/**
 * Copyright (c) 2006-2017, JGraph Ltd
 * Copyright (c) 2006-2017, Gaudenz Alder
 */
/**
 * Constructs a new point for the optional x and y coordinates. If no
 * coordinates are given, then the default values for <x> and <y> are used.
 * @constructor
 * @class Implements a basic 2D point. Known subclassers = {@link mxRectangle}.
 * @param {number} x X-coordinate of the point.
 * @param {number} y Y-coordinate of the point.
 */
StorageFile = function(ui, data, title)
{
	DrawioFile.call(this, ui, data);
	
	this.title = title;
};

//Extends mxEventSource
mxUtils.extend(StorageFile, DrawioFile);

/**
 * Sets the delay for autosave in milliseconds. Default is 2000.
 */
StorageFile.prototype.autosaveDelay = 2000;


/**
 * Sets the delay for autosave in milliseconds. Default is 20000.
 */
StorageFile.prototype.maxAutosaveDelay = 20000;

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.getMode = function()
{
	return App.MODE_BROWSER;
};

/**
 * Overridden to enable the autosave option in the document properties dialog.
 */
StorageFile.prototype.isAutosaveOptional = function()
{
	return true;
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.getHash = function()
{
	return 'L' + encodeURIComponent(this.getTitle());
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.getTitle = function()
{
	return this.title;
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.isRenamable = function()
{
	return true;
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.save = function(revision, success, error)
{
	this.saveAs(this.getTitle(), success, error);
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.saveAs = function(title, success, error)
{
	DrawioFile.prototype.save.apply(this, arguments);
	this.saveFile(title, false, success, error);
};


/**
 * 保存数据至服务器
 *
 */
var synData = "";
var synTitle = "";
var synId = "";
var ownerId = "";
function saveToServer(){
	//创建异步对象
	var xhr = new XMLHttpRequest();
	//设置请求的类型及url
	xhr.open('post', DRAW_SERVER_URL + '/save');
	//post请求一定要添加请求头才行不然会报错
	xhr.setRequestHeader("Content-type","application/json");
	xhr.setRequestHeader("uuid", synId);
	xhr.setRequestHeader("name", synTitle);
	xhr.setRequestHeader("ownerId", ownerId);
	//发送请求
	xhr.send(synData);
	xhr.onreadystatechange = function () {
		// 这步为判断服务器是否正确响应
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("saveToServer Response:", xhr.responseText);
		}
	};

}

function getUrlParam(paramName) {
	paramValue = "", isFound = !1;
	if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
		arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
		while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
	}
	return paramValue == "" && (paramValue = null), paramValue
}

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.saveFile = function(title, revision, success, error)
{
	synData = this.getData();
	synTitle = title;
	synId = getUrlParam("uuid");
	ownerId = getUrlParam("ownerId");
	console.log("storageFile synData:" , synTitle, synData); //返回一个对象
	saveToServer();
	if (!this.isEditable())
	{
		if (success != null)
		{
			success();
		}
	}
	else
	{
		var fn = mxUtils.bind(this, function()
		{
			if (this.isRenamable())
			{
				this.title = title;
			}
			
			try
			{
				this.ui.setLocalData(this.title, this.getData(), mxUtils.bind(this, function()
				{
					this.setModified(false);
					this.contentChanged();
					
					if (success != null)
					{
						success();
					}
		        }));
			}
			catch (e)
			{
				if (error != null)
				{
					error(e);
				}
			}
		});
		
		// Checks for trailing dots
		if (this.isRenamable() && title.charAt(0) == '.' && error != null)
		{
			error({message: mxResources.get('invalidName')});
		}
		else
		{
			this.ui.getLocalData(title, mxUtils.bind(this, function(data)
			{
				if (!this.isRenamable() || this.getTitle() == title || data == null)
				{
					fn();
				}
				else
				{
					this.ui.confirm(mxResources.get('replaceIt', [title]), fn, error);
				}
			}));
		}
	}
};

/**
 * Translates this point by the given vector.
 * 
 * @param {number} dx X-coordinate of the translation.
 * @param {number} dy Y-coordinate of the translation.
 */
StorageFile.prototype.rename = function(title, success, error)
{
	var oldTitle = this.getTitle();

	if (oldTitle != title)
	{
		this.ui.getLocalData(title, mxUtils.bind(this, function(data)
		{
			var fn = mxUtils.bind(this, function()
			{
				this.title = title;
				
				// Updates the data if the extension has changed
				if (!this.hasSameExtension(oldTitle, title))
				{
					this.setData(this.ui.getFileData());
				}
				
				this.saveFile(title, false, mxUtils.bind(this, function()
				{
					this.ui.removeLocalData(oldTitle, success);
				}), error);
			});
			
			if (data != null)
			{
				this.ui.confirm(mxResources.get('replaceIt', [title]), fn, error);
			}
			else
			{
				fn();
			}
		}));
	}
	else
	{
		success();
	}
};

/**
 * Returns the location as a new object.
 * @type mx.Point
 */
StorageFile.prototype.open = function()
{
	DrawioFile.prototype.open.apply(this, arguments);

	// Immediately creates the storage entry
	this.saveFile(this.getTitle());
};

/**
 * Adds the listener for automatically saving the diagram for local changes.
 */
StorageFile.prototype.getLatestVersion = function(success, error)
{
	this.ui.getLocalData(this.title, mxUtils.bind(this, function(data)
	{
		success(new StorageFile(this.ui, data, this.title));
	}));
};

/**
 * Stops any pending autosaves and removes all listeners.
 */
StorageFile.prototype.destroy = function()
{
	DrawioFile.prototype.destroy.apply(this, arguments);
	
	if (this.storageListener != null)
	{
		mxEvent.removeListener(window, 'storage', this.storageListener);
		this.storageListener = null;
	}
};
