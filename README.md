# [미니프로젝트] 연차/당직 프로젝트 만들기 - 백엔드
## API 구현 조건

- (필수) 모든 API 구현 시 Controller단에서 벨리데이션 로직 구현 (Null, 공백, 자리수,  패턴) 등
- (필수) 벨리데이션, 예외상황, 실패 등 오류 메세지 포맷 공통 JSON 응답처리
- (필수) API 구현 시 HttpMethod : 조회 GET, 등록/수정/삭제 : POST
- (필수) API 구현 시 서버에서 프론트엔드(클라이언트) 로 모든 응답은 JSON 처리
- (선택) API 테스트 및 프론트엔드 공유 및 협업용 Swagger 라이브러리 적용


## feature/login 
- Spring Security 적용
- Jwt Token 인증방식 적용
- Login 성공 후 마지막 로그인 성공 날짜 업데이트 적용
- Login 성공 후 회원번호, User-agent, Client IP, 시간 등 로그 테이블에 등록 처리
- 로그인 API 가이드 및 구현 필요.
