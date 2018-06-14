//getBtn함수는 getJSON를이용하여 비동기로 JSON불러오고 이를이용하여 list-item들을 표시
//인수로 choice와 page를 받는다.  page는 더보기 클릭 시 더가져오기위한 인자
var elemLoader = findClass('loader',0);
var elemList;
var str;

// 탭 및 더보기 눌렀을 때 원하는 데이터 가져오기 위한 함수
function getBtn(category,page){
  var url = "/api/product/list/search?page="+page+"&category="+category;

  elemList = findId('list');

    //첫화면일때만 str 초기화 해준다.
  if(page==1){str ='';
  elemList.innerHTML = str;}

  // getBtn시작하면 loading화면 표시
  elemLoader.style.display='';
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'get',
        success: function done(result){
          if(result.content.length==0){
            alert("더이상 상품이 존재하지 않습니다.");
          }
          result=result.content;
            str +='<div class="row">'
            for(var i = 0; i<result.length; i++){
                //네개의 item을row안에표시하여 한줄에 item 4개만표현하기위한 if문
                if(i!=0&&i%4==0){
                    str+='</div><div class="row">'
                }
                //bootstrap 그리드시스템이용하여 1boon처럼 나오게하기 마지막 item right solid는 제거
                if(i%4==3){str += '<div class="col-xs-6 col-md-3 col-sm-6 deleteSolid"><a href="http://1boon.kakao.com/'+result[i].path+'">'
                }else{str += '<div class="col-xs-6 col-md-3 col-sm-6 list_item"><a href="http://1boon.kakao.com/'+result[i].path+'">'}
                str +='<div class="imgBox"><img src="/productImage/' + result[i].image+'"></div></a>'
                str += '<p class="textBox">'+result[i].title+'</p>'
                str +='<div class="countBox">'
                str += '<p class="count-num"><b>'+result[i].views+'명이</b> 봤어요</p>'
                str += '<p class="like-num"><b>'+result[i].likes+'명이</b> 좋아해요.</p>'
                str += '</div>'
                str += '<div class="btnBox">'
                str += '<img src="/image/cart_btn_0.png" alt="" class="cart_btn" name="">'
                str += '<img src="/image/like_btn_1.png" alt="" class="like_btn 1" name="">'

                str += '</div>'
                str += '</div>'
            }
            str +='</div>'
            // list에 str을 넣어서 데이터추가
            elemList.innerHTML = str;
            // loading이 끝나면 none으로 없애기
            elemLoader.style.display='none';
        }

    })
}
