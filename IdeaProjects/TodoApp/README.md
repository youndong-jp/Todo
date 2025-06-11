# ☑️ Java Todo Console App

Java 학습을 위한 콘솔 기반 Todo 리스트 프로그램입니다.  
객체지향 설계(OOP), 파일 입출력, 예외 처리, JUnit 테스트까지 학습 목적으로 직접 구현하였습니다.

---

## 🛠 기능 소개

- ✅ 할 일 추가 / 삭제 / 완료 처리
- 🗂 JSON 파일로 할 일 저장 및 불러오기
- 🧪 JUnit을 활용한 단위 테스트 적용
- ❌ 예외 상황 처리 (잘못된 입력, 인덱스 범위 초과 등)

---

## 📁 프로젝트 구조

```
java-todo-console/
├── src/                      ← 실제 로직
│   ├── Todo.java
│   ├── TodoService.java
│   ├── TodoManager.java
│   └── Main.java
├── test/                     ← 테스트 코드 (JUnit)
│   ├── TodoTest.java
│   ├── TodoServiceTest.java
│   └── TodoManagerTest.java
├── todos.json                ← 할 일 저장 파일
├── .gitignore
└── README.md
```

---

## 🧪 테스트

- JUnit 5 (`org.junit.jupiter.api`) 기반 테스트 작성
- 테스트 커버리지는 다음을 포함:
    - Todo 객체 생성 및 상태 변경
    - TodoService의 추가, 완료, 삭제 기능
    - TodoManager의 파일 저장/불러오기
    - 존재하지 않는 파일 로드 테스트 (예외 방어)

---

## 🎯 학습 포인트

- Java OOP 설계 감각 (클래스 분리, 책임 분리)
- 컬렉션(`ArrayList`), 제네릭 사용법
- try-with-resources를 활용한 안정적인 파일 처리
- JUnit 테스트 작성 습관
- Git/GitHub 협업 흐름 익히기

---

## 🚀 향후 확장 계획

- [ ] 완료된 할 일만 보기 기능
- [ ] 날짜별 할 일 저장 파일 자동 생성
- [ ] 키워드 검색 기능
- [ ] GUI 또는 REST API 버전으로 확장
- [ ] SQLite 연동 및 데이터베이스 버전 개발

---

## 👤 개발자

- **이름**: 윤동찬 (youndong-jp)
- **학습 목적**: Java & 백엔드 개발자 전직 준비
- **Velog 기록**: https://velog.io/@youndong-jp

---

## 📌 실행 방법

```bash
# 1. 컴파일
javac -d bin src/*.java

# 2. 실행
java -cp bin Main
```


