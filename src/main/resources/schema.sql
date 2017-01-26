CREATE TABLE IF NOT EXISTS stream_versions (
  stream_id VARCHAR(255) NOT NULL,
  stream_version BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (stream_id)
);

CREATE TABLE IF NOT EXISTS event_messages (
  event_id BIGINT NOT NULL AUTO_INCREMENT,
  stream_id VARCHAR(255) NOT NULL,
  stream_version BIGINT NOT NULL DEFAULT 0,
  event_type VARCHAR(255) NOT NULL,
  payload TEXT NOT NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (event_id),
  FOREIGN KEY (stream_id) REFERENCES stream_versions (stream_id)
);

CREATE INDEX IF NOT EXISTS idx_event_messages_stream_id
  ON event_messages (stream_id, stream_version);