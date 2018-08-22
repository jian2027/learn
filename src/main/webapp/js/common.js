
//return object getElementById
function $id(id){
  return document.getElementById(id);
}

//return object getElementsByName
function $name(name){
  return document.getElementsByName(name);
}

function show(id){
  className = getUnhidenClass(id);
  $id(id).className = className;
}

function hiden(id){
  className = $id(id).className;
  if(className.length == 0){ //表示沒有(length==0)定義class屬性.
    className = "hide";
  }else{
    if(className.indexOf("hide") == -1){  //表示有定義class屬性且屬性中不含(int==-1)隱藏("hide").
      className = className + " hide";
    }
  }
  $id(id).className = className;
}


function openProgressUrl(url,width,height) {
  var subWin = window.open(url,'','toolbar=no,menubar=no,location=no,scrollbars=no,resizable=yes,status=no,left=0,top=0,width=' + width + ',height=' + height);
  return subWin;
}

