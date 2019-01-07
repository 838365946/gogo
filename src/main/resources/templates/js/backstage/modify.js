onload = function(){
	modify_users();
	console.log("过来")
}

function modify_users(){

	var a9 = document.getElementsByClassName("modifyss");
	var a = 0;
		for(var i = 0;i<a9.length;i++){
			a9[i].onclick=function(){
                console.log("过来2")
				console.log(a9[i].c_name)
				var tr = this.parentElement.parentElement;
				var tds = tr.children;
				for(var i = 0;i<tds.length-1;i++){
					var statu = tds[i].attributes[1].value;
					if(statu =="save"){
						a = 1;
						tds[i].innerHTML = "<input  value = '"+tds[i].innerText+"'/>"

						tds[i].attributes[1].value= "update";
					}else{
						a = 2;
						var input = tds[i].children[0].value;
						tds[i].innerHTML = input;
						tds[i].attributes[1].value= "save";

				}
			}
				if(a ==2){
                    $(function () {
                        $.ajax({
                            url:"/passcheck",
                            type:"post",
                            data:"page=0",
                            dataType:"json",
                            success:function(data) {
                             console.log("盘他")

                            }
                        })
                    })

				}
		}

	}

}
