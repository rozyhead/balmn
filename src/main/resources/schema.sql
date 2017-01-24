CREATE TABLE IF NOT EXISTS versions (
  stream_id VARCHAR(255) NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (stream_id)
);

CREATE TABLE IF NOT EXISTS events (
  id BIGINT NOT NULL AUTO_INCREMENT,
  stream_id VARCHAR(255) NOT NULL,
  version BIGINT NOT NULL DEFAULT 0,
  event_type VARCHAR(255) NOT NULL,
  payload TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (stream_id) REFERENCES versions (stream_id)
);

CREATE INDEX IF NOT EXISTS idx_events_stream_id ON events (stream_id);