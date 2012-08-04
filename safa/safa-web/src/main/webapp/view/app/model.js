URLs = {
	tagTypes: "services/tags/getTypes.json",
	getTags: "services/tags/filterTags.json",
	getContents: "services/content/getContent.json",
	importantTags: "config/importantTags.json",
	getContent:"services/content/get.json"
	
};

Data = {
	clean: function(){
		var c = this.clean;
		Data = {clean:c};
	}
};

ViewConfig = {
	getImportantTags: function(me,callback){
		$.getJSON(URLs.importantTags,function(data){
			callback.call(me,data);
		});
	}
}

Daos = {
	getAllTagTypes : function(me, callback){
		$.getJSON(URLs.tagTypes,function(data){
			callback.call(me,data);
		});
	},
	getTags : function(me, selectedTags, callback){
		$.getJSON(URLs.getTags,selectedTags,function(data){
			callback.call(me,data);
		});
	},
	getContents: function(me,contents,callback){
		$.getJSON(URLs.getContents,contents,function(data){
			callback.call(me,data);
		});
	},
	getContent: function(me, number,callback){
		$.getJSON(URLs.getContent,number,function(data){
			callback.call(me,data);
		});
	}
};
