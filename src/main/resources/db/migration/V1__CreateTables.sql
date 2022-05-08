CREATE TABLE IF NOT EXISTS course
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS question
(
    id             SERIAL PRIMARY KEY,
    course_id      SERIAL NOT NULL REFERENCES course (id),
    question       TEXT   NOT NULL,
    correct_answer TEXT   NOT NULL,
    answer_one     TEXT   NOT NULL,
    answer_two     TEXT   NOT NULL,
    answer_three   TEXT   NOT NULL,
    answer_four    TEXT   NOT NULL
);

CREATE TABLE IF NOT EXISTS student
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS student_answer
(
    id                        SERIAL PRIMARY KEY,
    student_id                SERIAL  NOT NULL REFERENCES student (id),
    question_id               SERIAL  NOT NULL REFERENCES question (id),
    number_of_correct_answers INTEGER NOT NULL DEFAULT 0
);
