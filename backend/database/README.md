# Noto
- docker compose up -d 명령어 입력 시 postgres DB가 백그라운드로 실행 됨
- init.sql에 초기 DB DDL 작성/수정정 후 docker composse down, docker compose up -d 하면 수정된 스키마 반영 됨
- docker logs -f noto_postgres 명령으으로 Container 내의 로그 확인 가능
- docker exec -it noto_postgres psql -U postgres -d noto 명령 입력 이후 DB 내에서 쿼리문 실행 가능
