# search-api

## 실행파일 ##
#### Executable jar : search-api-0.0.1-SNAPSHOT.jar ####
#### 인메모리 DB : redis (port : 6379) ####

-------------

## API 명세 ##

## 1. 블로그 검색 ##

### 기본정보 ###
```
/api/blog ( GET )
```

### Request ###

- query
  - String
  - 검색을 원하는 질의어, 특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백(' ') 구분자로 넣을 수 있음
  - Required : O
- sort
  - String
  - 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
  - Required : X
- page
  - Integer
  - 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
  - Required : X
- size
  - Integer
  - 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
  - Required : X


### Response ###

#### meta ####
- total_count
  - Integer
  - 검색된 문서 수
- pageable_count
  - Integer
  - total_count 중 노출 가능 문서 수
- is_end
  - Boolean	
  - 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음

#### documents ####
- title
  - String
  - 블로그 글 제목
- contents
  - String
  - 블로그 글 요약
- url
  - String
  - 블로그 글 URL
- blogname
  - String
  - 블로그의 이름
- thumbnail
  - String
  - 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음
- datetime
  - Datetime
  - 블로그 글 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]


## 2. 인기 검색어 목록 ##

### 기본정보 ###
```  
/api/blog/rank ( GET )
```

### Response ###
- word
  - String
  - 검색어
- cnt
  - Integer
  - 검색 횟수 