// dom 객체 찾는 js모듈정의

//id dom객체 찾는 메서드
function findId(name){
  return document.getElementById(name);
}
//class dom객체 찾는 메서드
function findClass(name, index){
  return document.getElementsByClassName(name)[index];
}

//태그 dom객체 찾는 메서드
function findTag(name, index){
  return document.getElementsByTagName(name)[index];
}
//jquery처럼 쿼리로 가져오기
function findSelector(name){
  return document.querySelector(name);
}
//jquery처럼 모든쿼리 가져오기
function findSelectorAll(name){
  return document.querySelectorAll(name);
}
