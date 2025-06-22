# ☑️ Java Todo Console App

콘솔 환경에서 동작하는 Java 기반 Todo 리스트 프로그램입니다.  
객체지향 설계, 파일 입출력, 예외 처리, JUnit 테스트, 자료구조 활용까지  
학습 목적으로 하나씩 직접 구현하며 백엔드 개발자로서의 기초 체력을 다졌습니다.

---

## 📌 프로젝트 목적
- 우테코 8기 지원 전 자체 학습 
- Java 기본 문법과 OOP 구조를 실습하며 익히기
- 실전에서 필요한 파일 처리, 예외 대응, 테스트 작성 경험 쌓기
- 간단한 CLI 기반 기능 구현을 통해 추후 웹/DB 확장 기반 마련

---

## ⚙️ 기술 스택 및 환경

| 항목 | 내용 |
|------|------|
| Language | Java 17 |
| Build Tool | javac / IDE 직접 컴파일 |
| Test | JUnit 5 (`org.junit.jupiter.api`) |
| JSON 처리 | Gson |
| 실행 환경 | 콘솔 기반 (CLI) |

---

## 🧩 프로젝트 구조

```
java-todo-console/
├── src/
│   ├── LocalDateAdapter.javaTodo.java
│   ├── Main.java
│   ├── Todo.java
│   ├── TodoManager.java
│   └── TodoService.java
├── test/
│   ├── TodoManagerTest.java
│   ├── TodoServiceTest.java
│   └── TodoTest.java
├── todos.json
├── todos.csv
└── README.md
```

---

## 🛠 구현 기능 목록

| 기능 | 상태 | 설명 |
|------|---|------|
| 할 일 추가/삭제/완료 처리 | ✅ | 콘솔 입력 기반 CRUD |
| JSON 저장/불러오기 | ✅ | 프로그램 종료 후에도 유지 |
| 예외 처리 | ✅ | 입력값 검증 및 방어 처리 |
| JUnit 테스트 | ✅ | Todo/Service/Manager 단위 테스트 |
| 키워드 검색 기능 | ✅ | 대소문자 구분 없이 필터링 |
| 카테고리 기능 | ✅ | 할 일 항목에 분류 추가 |
| CSV 내보내기 기능 | ✅ | todos.csv로 저장 |
| Undo 기능 | ✅ | 삭제 항목 복구 (Stack) |
| 자동 저장 기능 | ⏳ | 주요 동작마다 저장 수행 |

---

## 🧪 테스트 항목

- Todo 객체 생성, 완료 처리
- TodoService: 추가, 삭제, 완료 로직
- TodoManager: JSON 입출력
- 예외 처리: 잘못된 입력, 파일 없음

---

## 🚀 향후 확장 계획

- [ ] GUI 기반 프로그램 (JavaFX/Swing)
- [ ] MySql 또는 JDBC 기반 데이터베이스 연동
- [ ] 웹 서버 기반 REST API 버전 (Spring Boot)
- [ ] 사용자 로그인/세션 관리 기능 추가
- [ ] 테스트 커버리지 향상 및 CI 연결

---

## ✍️ 학습 포인트

- 클래스 설계와 책임 분리
- 컬렉션(ArrayList), Stack 등 자료구조 활용
- 파일 입출력 흐름과 JSON, CSV 실습
- JUnit 단위 테스트 작성 및 테스트 주도 습관
- GitHub 기반 버전 관리 및 README 문서화

---

## 👤 개발자 정보

- **이름**: 윤동찬 (youndong-jp)
- **목표**: 백엔드 개발자로 전직 준비 중
- **블로그**: https://velog.io/@youndong-jp

---

## 💻 실행 방법

```bash
# 1. 컴파일
javac -d bin src/*.java

# 2. 실행
java -cp bin Main
```
