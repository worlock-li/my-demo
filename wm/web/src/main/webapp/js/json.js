$.fn.extend({
	serializeJson : function() {
		var json = {};
		
		var msg = this.serializeArray();
		$(msg).each(function() {
			if (json[this.name]) {
				
				if (!json[this.name].push) { 
					json[this.name] = [ json[this.name] ];
				}
				json[this.name].push(this.value || ''); 
			} else {
				json[this.name] = this.value || '';
			}
		});
		return json
	}
});
$.fn.populateForm = function(data){
    return this.each(function(){
        var formElem, name;
        if(data == null){this.reset(); return; }
        for(var i = 0; i < this.length; i++){  
            formElem = this.elements[i];
            name = (formElem.type == "checkbox")? formElem.name.replace(/(.+)\[\]$/, "$1") : formElem.name;
            if(data[name] == undefined) continue;
            switch(formElem.type){
                case "checkbox":
                    if(data[name] == ""){
                        formElem.checked = false;
                    }else{
                        if(data[name].indexOf(formElem.value) > -1){
                            formElem.checked = true;
                        }else{
                            formElem.checked = false;
                        }
                    }
                break;
                case "radio":
                    if(data[name] == ""){
                        formElem.checked = false;
                    }else if(formElem.value == data[name]){
                        formElem.checked = true;
                    }
                break;
                case "button": break;
                default: formElem.value = data[name];
            }
        }
    });
};