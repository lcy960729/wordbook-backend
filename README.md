# wordbook-backend
단어장 백엔드 서버

## 개발 목적
Spring Boot Framework와 TDD, RestAPI를 학습하기 위한 프로젝트입니다.

## 개발 과정
https://lcy960729.github.io/category/wordbook-project.html

## ERD
![ERD](https://user-images.githubusercontent.com/58020519/108622313-44dbff80-747b-11eb-96e4-ccc9be21fbaa.png)
* User는 사용자 입니다. 사용자는 여러개 개인 단어장을 가질 수 있으며 여러 스터디 그룹에 참여할 수 있습니다.
* StudyGroup은 스터디 그룹입니다. 스터디 그룹은 여러 사용자가 참여할 수 있으며 여러개의 그룹 단어장을 가질 수 있습니다.
* Study는 User와 StudyGroup의 관계입니다. 스터디 관계는 유저와 그룹을 연결하며 유저가 그룹의 어떤 권한을 가지는지 나타냅니다.
* WordBook은 단어장입니다. 단어장을 이름과 타입을 가지며 타입을 통해 상속된 단어장들을 불러옵니다.
* UserWordBook은 유저 개인 단어장입니다. UserWordBook은 WordBook을 상속 받습니다.
* StudyGroupWordBook은 그룹 단어장입니다. StudyGroupWordBook은 WordBook을 상속 받습니다.
* Word는 단어입니다. WordBook은 여러 Word를 가지고 있습니다.

## API 예제
### 사용자를 생성하는 API 예
![API 예제](https://user-images.githubusercontent.com/58020519/108622330-6d63f980-747b-11eb-8e63-4289319311cc.png)
사용자를 생성하는 API의 요청과 결과 예제 입니다. 이외 추가적인 API의 구현 내용은 아래 링크에서 확인할 수 있습니다.

자세한 개발 내용 - https://lcy960729.github.io/wordbook-project/2021/01/17/%EB%8F%84%EB%A9%94%EC%9D%B8-%EA%B8%B0%EB%8A%A5%EB%93%A4-%EA%B5%AC%ED%98%84-%EC%99%84%EB%A3%8C.html

## 개발 환경
JAVA : Oracle JDK 1.8.0
