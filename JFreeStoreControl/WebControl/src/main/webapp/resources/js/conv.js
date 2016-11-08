				({  
					toArray : function (object) {
						 return [].slice.call(object);
					},
					getChar : function (event) {
					 if (event.which == null) { // IE
    					if (event.keyCode < 32) return null; // ����. ������
    					return String.fromCharCode(event.keyCode)
  					 }
  					 if (event.which != 0 && event.charCode != 0) { // ��� ����� IE
    					if (event.which < 32) return null; // ����. ������
    					return String.fromCharCode(event.which); // ���������
  					 }
  					 return null; // ����. ������	
					},
					chechInput : function (e) {
                     	e = e || event;
      				    var chr = this.getChar(e);
      					if (e.ctrlKey || e.altKey || chr == null) return; // ����������� �������
      					if ((chr < "0" || chr > "9") && chr != "." ) return false;
					},
					
					
					makeCalculations : function (type,event) {
					var trgt = event.currentTarget,
						    target = ( type === "UANtoUSD") ? trgt.form.purchaseUsdPrice : trgt.form.purchasePrice ,
						  	currency = +trgt.form.purchaseExchange.value,
						    currentValue = + trgt.value,
						    result = ( type === "UANtoUSD") ? ( currentValue / currency) : ( currentValue * currency) ;
						      return target.value = result.toFixed(2) || "ошибка!" ;
					},
					
					makeCalculations2 : function (type,event) {
					var trgt = event.currentTarget,
						    target = ( type === "UANtoUSD2") ? trgt.form.sellUsdPrice : trgt.form.sellPrice ,
						  	currency = +trgt.form.sellExchange.value,
						    currentValue = + trgt.value,
						    result = ( type === "UANtoUSD2") ? ( currentValue / currency) : ( currentValue * currency) ;
						    return target.value = result.toFixed(2) || "ошибка!" ;
					},
					
					getAllFormsInDocument : function (initClass) {
						return document.querySelectorAll(initClass); 
					},
					
					init : function (initClass) {
						 var nodes = this.toArray(this.getAllFormsInDocument(initClass));
						 nodes.forEach( function(formNode) {
						 	var formElem = formNode.elements,
						 	    uanNode = formElem.purchasePrice,
						 	    usdNode = formElem.purchaseUsdPrice,
								uanNode2 = formElem.sellPrice,
						 	    usdNode2 = formElem.sellUsdPrice;
								
						    uanNode.onkeypress = this.chechInput.bind(this);
						    uanNode.oninput = this.makeCalculations.bind(this,"UANtoUSD");
						    usdNode.onkeypress = this.chechInput.bind(this);
						    usdNode.oninput = this.makeCalculations.bind(this,"USDtoUAN");
							
							uanNode2.onkeypress = this.chechInput.bind(this);
						    uanNode2.oninput = this.makeCalculations2.bind(this,"UANtoUSD2");
						    usdNode2.onkeypress = this.chechInput.bind(this);
						    usdNode2.oninput = this.makeCalculations2.bind(this,"USDtoUAN2");
						 }.bind(this))
					}
				}).init(".curr-converter-on");