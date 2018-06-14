url 규칙
모든 자원에 대한 기본 crud는
자원/{id} get
자원/list get
자원 post
자원 put
자원/{id} delete

규칙이 다르다면 params를 이용 (ex 자원에 대한 다른 조건으로 검색 시)
자원/search?username=  get 으로 표현

user에 대한 정책 업데이트 필요
