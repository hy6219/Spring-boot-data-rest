# 처음 배우는 스프링 부트2-Chapter6 데이터 레스트

스프링 부트를 이용해서 RESTful API를 어떻게 다루는 지 궁금해서 4장 이후에
바로! 공부하게 되었다❤

CORS를 화면단 기초를 공부할때 스쳐가듯 들었지만,
직접 구현해보니 정말 신기하면서도 재미있었다!

여러번 반복해봐야 할 것 같은데, CORS를 이용하면 서버 보안을 조금 더 강화시키는 데에
효과적일 것 같다!

💚 이번 공부 내용에서 시도해본 것 💚

- CORS 지원 : 클라이언트 포트에서 같은 프로젝트에 접근해도 서버측의 프로젝트, 즉 같은
프로젝트로 인식해서 알맞게 자원을 공유해줄 수 있다

- 모듈을 루트 프로젝트로 관리

- gradle 7 버전 이상부터는 compile 이 지원되지 않고 implementation, testImplementation이 지원됨

- HATEOAS : 링크와 리소스로 하이퍼미디어를 지원

- 스프링 시큐리티를 build.gradle에서 추가해주면, 처음에는 기본적으로 401 Unauthorized 에러가 뜨는데,
콘솔에 뜬 비밀번호와 username으로 "user"를 입력해주면 접근가능하다! 

![Spring Data Rest](https://github.com/hy6219/TIL/blob/main/Spring/CORS/%EA%B5%90%EC%B0%A8%20%EC%B6%9C%EC%B2%98%20HTTP%20%EC%9A%94%EC%B2%AD%EC%9D%84%20%ED%97%88%EC%9A%A9%ED%95%B4%EC%A3%BC%EA%B8%B0.gif?raw=true)