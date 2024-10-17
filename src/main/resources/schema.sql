CREATE TABLE board (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- created_date는 게시글이 생성될 때 한번만 current_timestamp
    updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    -- updated_date는 게시글이 수정될 때마다 계속 current_timestamp (ON UPDATE CURRENT_TIMESTAMP)

);