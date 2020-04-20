CREATE TABLE category (
  cat_id INT NOT NULL GENERATED ALWAYS AS IDENTITY
       CONSTRAINT category_pk PRIMARY KEY,
  cat_name VARCHAR(50)
);

CREATE TABLE word (
  wrd_id INT CONSTRAINT wrd_id_not_null
       NOT NULL GENERATED ALWAYS AS IDENTITY
       CONSTRAINT word_pk PRIMARY KEY,
  wrd_german VARCHAR(50),
  wrd_english VARCHAR(50),
  wrd_cat_id INT CONSTRAINT wrd_category_fk
         REFERENCES category(cat_id)
);

CREATE TABLE event_type (
    et_id INT CONSTRAINT et_not_null NOT NULL
        GENERATED ALWAYS AS IDENTITY
        CONSTRAINT event_type_pk PRIMARY KEY,
    et_name VARCHAR(50)
);

CREATE TABLE event (
    evt_id INT CONSTRAINT evt_id_not_null NOT NULL
        GENERATED ALWAYS AS IDENTITY
        CONSTRAINT event_pk PRIMARY KEY,
    evt_et_id INT CONSTRAINT evt_event_type_fk
        REFERENCES event_type(et_id),
    evt_date DATE,
    evt_descr VARCHAR(200)
);

CREATE TABLE card (
    crd_id INT CONSTRAINT crd_id_not_null NOT NULL
              GENERATED ALWAYS AS IDENTITY
              CONSTRAINT card_pk PRIMARY KEY,
    crd_correct_counter INT DEFAULT 0,
    crd_fail_counter INT DEFAULT 0,
    crd_wrd_id INT CONSTRAINT crd_word_fk REFERENCES word(wrd_id),
    crd_evt_id INT CONSTRAINT crd_event_fk REFERENCES event(evt_id)
)