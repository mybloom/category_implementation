# 온라인 쇼핑몰 상품 카테고리 구현 

## ✅ 버젼 정보
- JDK 11
- Spring Boot 2.6.6

## ✅ 웹서버 설치/빌드
```
git clone https://github.com/mybloom/category_implementation.git
cd category_implementation
chmod +x gradlew
./gradlew build
nohup java -jar category-0.0.1.jar & >> category_log.log
```

### JDK 설치 추가 정보 : 리눅스 centOS 기준  
```
- JDK 11 다운로드
wget https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz

- 압축 풀기 
tar -zxvf openjdk-11.0.2_linux-x64_bin.tar.gz

- 환경변수 등록
vi /etc/profile
export JAVA_HOME=/usr/lib/jdk-11.0.2/bin

- 적용 및 확인
source /etc/profile
echo $JAVA_HOME

- Alias 설정 
vi /etc/bashrc
alias java="/usr/lib/jdk-11.0.2/bin/java"
alias javac="/usr/lib/jdk-11.0.2/bin/javac"

- 적용 및 확인
source /etc/bashrc
java -version
javac -version
```

---

## ✅ 요구사항 분석서 

- [요구사항 분석서](docs/requirement.md)

---

## ✅ API 목록

### 카테고리 조회

- [전체 카테고리 조회](docs/apiSelectAll.md)
- [하위 카테고리 조회](docs/apiSelectByParentId.md)
- [카테고리 상세 조회](docs/apiSelectDetail.md)

### 카테고리 생성
- [카테고리 생성](docs/apiCreate.md)

### 카테고리 수정
- [카테고리 수정](docs/apiModify.md)

### 카테고리 삭제
- [카테고리 삭제](docs/apiDelete.md)

---

## ✅ DB ERD
<img src ="https://user-images.githubusercontent.com/55780251/179400581-cb92206c-1abc-4bbc-8636-37f5d562440b.jpg">

