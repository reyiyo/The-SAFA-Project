// FUnctiones de utilidad

// Curryficador.
Function.prototype.partial = function(){
	  var n,
	  aps = Array.prototype.slice,
	  orig_args = aps.call( arguments ),
	  __method = this;

	  if ( arguments.length < 1 ) {
	    return this; // nothing to curry with - return function
	  }

	  if ( typeof __method === 'number' ) {
	    n = __method;
	    __method = orig_args.shift();
	  } else {
	    n = __method.length;
	  }

	  return function() {
	    var args = orig_args.concat( aps.call( arguments ) );
	    return args.length < n
	      ? __method
	      : __method.apply( this, args );
	  };
	};
	
Function.prototype.bind = function( ){
		  var  aps = Array.prototype.slice,
		  args = aps.call( arguments ),
		  __method = this;
		  return function() {
		    return __method.apply( this, args.concat( aps.call( arguments ) ) );
		  };
		};
		
// **************************


States = {
	search:function(){
		Controllers.Search.init();
		Controllers.Search.execute(Views.Search.render,
		Views.Search.bindEvents);
		Views.Search.myController = Controllers.Search;
	},
	resultList:function(list){
		Controllers.Results.init(list);
		Controllers.Results.execute(Views.Resultlist.render,
		Views.Resultlist.bindEvents);
		Views.Resultlist.myController = Controllers.Results;
	}
};

window.location.hash = "";

$(window).bind('hashchange', function(evt) {
  console.log(evt);
  var str=window.location.hash;
  if(str.indexOf("#!/content/")==0){ // Mostrar datos del contenido.
	  Daos.getContent(this,str.substring(11),function(data){
		  Views.Resultlist.showContent(data);
	  });
  } else if(str.indexOf("#!/search") == 0){
	  Views.Resultlist.goBackSearch();
  }
});




// Binds the data with the template.
TemplatizeSelector = function(tpl,data){
	return Mustache.to_html($(tpl).html(),data);
}

// Set, into $(selectorWhere), the $(selectorWhat) template binded with
// the "data" object
SetElement = function(selectorWhere,selectorWhat,data){
	$(selectorWhere).html(
		TemplatizeSelector(selectorWhat,data)
	);
}


Views = {
	ShowContent:{
		render:function(dto){
		
		}
	},
	Resultlist:{
		render:function(dto){
			
				
			// Obtengo los tags importantes, y se los agrego al dto.
			ViewConfig.getImportantTags(this,function(tagsImportantes){
				$.each(dto.contents,function(i,elem){
					elem.importantTags = [];
					$.each(elem.tags,function(j,tag){
						if(tagsImportantes.indexOf(tag.tagType.tagName) != -1){
							elem.importantTags.push(tag.tagType.tagName + ": " +tag.value)
						}
					});
				});
				// Una vez que lo hice, muestro toda la info.
				SetElement("#mainArea","#tplContentList",dto);
				// Seteo todas las imagenes.
				$(".capty").each(function(i,elem){
					$(elem).capty({
					  height:   80,
					  opacity:  .8,
					  animation: 'fixed'
					});
				});
				$(".popovered").each(function(i,elem){
					$(elem).popover();
				});
			});
		},
		bindEvents:function(){
			
		},
		actualSearch:null,
		showContent:function(dto){
			$(".popovered").each(function(i,elem){
					$(elem).popover('hide');
				});
			Views.Resultlist.actualSearch = $("#mainArea").html();
			
			SetElement("#mainArea","#tplContent",dto);
			SetElement("#sidebar","#tplCommentSidebar",dto);
		},
		goBackSearch:function(){
			
			$("#mainArea").html(Views.Resultlist.actualSearch);
			$(".popovered").each(function(i,elem){
					$(elem).popover();
				});
		},
		myController:[]
	},
	Search:{
		myController:null,
		lastState:null,
		render:function(dto,callback){
			// Me fijo si tiene un lastState.
			if(Views.Search.lastState != null){
				$("#sidebar").html(Views.Search.lastState.sidebar);
				$("#mainArea").html(Views.Search.lastState.main);
			} else {
				var viewElements = {};
				//Agarro cada elemento del DTO, y le agrego el correspondiente textbox.
				$.each(dto.tagTypes ,function(index,elem){
					elem["tagTextbox"] = TemplatizeSelector("#tplSelect"+elem.tagDataType,elem);
				});
				
				viewElements["select"] = TemplatizeSelector("#tplCombobox",dto);       // Generates the Combobox.
				//viewElements["textbox"] = TemplatizeSelector("#tplSelectSTRING");      // Creates the textbox
				
				SetElement("#mainArea","#tplSearch",viewElements);
			}
		},
		beforeExitState: function(){
			// Guardo el sidebar y el main:
				var sidebarState = $("#sidebar").html();
				var mainState = $("#mainArea").html();
				Views.Search.lastState = {
					sidebar: sidebarState,
					main: mainState
				};
		},
		bindEvents: function(handler){
			$(".withDatepicker").each(function (id,elem){
				$(elem).datepicker();
			});
			$("[killparent]").live("click",function(evt){
				$(evt.target.parentNode).remove();
			});
			$("[killgrandparent]").live("click",function(evt){ //.delete.selectedTag 
					$(evt.target.parentNode.parentNode).remove();
			});
			$("#dateTo").keypress(function(evt){
				if(evt.keyCode==13) 
					Views.Search.addTagDoubleDate();
			});
			$(".search").autocomplete({
					source:function(args,callback){
							Daos.getTags(this,"",function(elem){
								var response = [];
								$.each(elem,function(i,e){
									response.push(e.value);
								});
								callback(response);
							  });
							}
		    });
			$(".btn.initSearch.single").live("click",Views.Search.addTag);
			$(".btn.initSearch.date").live("click",Views.Search.addTagDoubleDate);
			$(".search.submitOnEnter").live("keypress",Views.Search.addTag);
			$("#initSearch").live("click",Views.Search.search.partial(handler));
		},
		search: function(handler, evt){
			States.resultList(Views.Search.tagList);
		},
		addTagDoubleDate:function(){
			Views.Search.tagList.push({"tagType":$(".active a").html(),"tagValue":"desde " + $("#dateFrom").val() + " hasta el " + $("#dateTo").val()});
			var lst = {tags:Views.Search.tagList};
			SetElement("#sidebar","#tplTaglist",lst)
			return false;
		},
		tagList:[],
		addTag:function(evt){
			if(evt.keyCode==13){
				//Primero, le aviso al control de que hay un nuevo tag.
				
				Views.Search.tagList.push({"tagType":$(".active a").html(),"tagValue": $(this).val()});
				var lst = {tags:Views.Search.tagList};
				SetElement("#sidebar","#tplTaglist",lst)
				return false;
			}
		}
	}
}

Controllers = {
	Search:{
		toView:[],
		
		init : function(){},
		
		execute : function(view,events){
			Daos.getAllTagTypes(this,function(tagTypes){
				this.toView["tagTypes"] = tagTypes;
				view(this.toView);
				events(this);
			});
		},
		changeTagTypes : function(evt){
				console.log("cambie");
				console.log(evt);
		},
		autocomplete:function(request,response){
			console.log(request);
			console.log(response);
		},
		
		searchList : function(args){
			Views.Search.beforeExitState();
			State.resultList(args)
		}
	},
	Results:{
		queryList: [],
		init: function(qList){
			this.queryList = qList;
		},
		toView:{},
		execute: function(view,events){
			Daos.getContents(this,this.queryList,function(contents){
					this.toView["contents"] = contents;
					view(this.toView);
					events(this);
				});
		}
	}
};

