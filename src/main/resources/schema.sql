CREATE TABLE accounts (
  account_name VARCHAR(255) NOT NULL,
  is_group_account BOOLEAN NOT NULL DEFAULT false,
  PRIMARY KEY (account_name)
);

CREATE TABLE boards (
  account_name VARCHAR(255) NOT NULL,
  board_name VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (account_name, board_name)
);

CREATE TABLE sheets (
  sheet_id VARCHAR(36) NOT NULL,
  sheet_name VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (sheet_id)
);

CREATE TABLE board_sheets (
  account_name VARCHAR(255) NOT NULL,
  board_name VARCHAR(255) NOT NULL,
  seq INTEGER NOT NULL,
  sheet_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (account_name, board_name, seq),
  FOREIGN KEY (account_name, board_name) REFERENCES boards (account_name, board_name),
  FOREIGN KEY (sheet_id) REFERENCES sheets (sheet_id)
);

CREATE UNIQUE INDEX idx_board_sheets_1
  ON board_sheets (sheet_id);

CREATE TABLE cards (
  card_id VARCHAR(36) NOT NULL,
  card_title VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (card_id)
);

CREATE TABLE sheet_cards (
  sheet_id VARCHAR(36) NOT NULL,
  seq INTEGER NOT NULL,
  card_id VARCHAR(36) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (sheet_id, seq),
  FOREIGN KEY (sheet_id) REFERENCES sheets (sheet_id),
  FOREIGN KEY (card_id) REFERENCES cards (card_id)
);

CREATE UNIQUE INDEX idx_sheet_cards_1
  ON sheet_cards (card_id);

CREATE TABLE comments (
  comment_id VARCHAR(36) NOT NULL,
  card_id VARCHAR(36) NOT NULL,
  comment_content TEXT NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_by VARCHAR(255) NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (comment_id),
  FOREIGN KEY (card_id) REFERENCES cards (card_id)
);
