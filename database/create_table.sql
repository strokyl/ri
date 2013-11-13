DROP SCHEMA IF EXISTS ri CASCADE;
create schema ri;

CREATE TABLE ri.document (
    document_id SERIAL PRIMARY KEY,
    url_xml  VARCHAR(256) UNIQUE NOT NULL,
    sat Integer NOT NULL,
    date DATE NULL
);

CREATE TABLE ri.paragraphe (
    paragraphe_id SERIAL PRIMARY KEY,
    xpath VARCHAR(256) NOT NULL,
    sat Integer NOT  NULL,
    document_id Integer references ri.document(document_id),
    UNIQUE(document_id, xpath)
);

CREATE TABLE ri.terme (
    terme_id SERIAL PRIMARY KEY,
    racine VARCHAR(256) UNIQUE,
    ipf REAL NOT NULL
);

CREATE TABLE ri.terme_paragraphe (
    terme_paragraphe_id SERIAL PRIMARY KEY,
    tf REAL NOT NULL,
    tf_robertson REAL NOT NULL,
    terme_id Integer references ri.terme(terme_id),
    paragraphe_id Integer references ri.paragraphe(paragraphe_id),
    UNIQUE(terme_id, paragraphe_id)
);

CREATE TABLE ri.apparition_type (
    apparition_type_id SERIAL PRIMARY KEY,
    kind VARCHAR(256) UNIQUE NOT NULL,
    ponderation REAL
);

CREATE TABLE ri.apparition (
    apparition_id SERIAL PRIMARY KEY,
    nombre Integer NOT NULL,
    terme_paragraphe_id Integer references ri.terme_paragraphe(terme_paragraphe_id),
    apparition_type_id Integer references ri.apparition_type(apparition_type_id),
    UNIQUE(apparition_id, terme_paragraphe_id)
);

CREATE TABLE ri.position (
    position_id SERIAL PRIMARY KEY,
    position Integer NOT NULL,
    apparition_id Integer references ri.apparition(apparition_id)
);

INSERT INTO ri.apparition_type(kind, ponderation) VALUES ('paragraphe',1.0);
INSERT INTO ri.apparition_type(kind, ponderation) VALUES ('titre',2.0);
INSERT INTO ri.apparition_type(kind, ponderation) VALUES ('description',1.5);
INSERT INTO ri.apparition_type(kind, ponderation) VALUES ('sous titre',1.75);
