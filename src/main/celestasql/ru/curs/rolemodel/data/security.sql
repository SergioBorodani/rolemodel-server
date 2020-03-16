CREATE SCHEMA security version '1.0';

CREATE TABLE Poljzovatelj (
    sid VARCHAR(50) NOT NULL,
    login VARCHAR(50),
    CONSTRAINT Poljzovatelj_sid PRIMARY KEY(sid)
);

-------------------------------------------------------------------

CREATE TABLE Rolj (
  rolj VARCHAR(50) NOT NULL,
  nazvanie VARCHAR(150),
  CONSTRAINT Rolj_rolj PRIMARY KEY(rolj)
);

------------------------------------------------------------------------

CREATE TABLE Razr (
  razr VARCHAR(50) NOT NULL,
  nazvanie VARCHAR(150),
  CONSTRAINT Razr_razr PRIMARY KEY(razr)
);

------------------------------------------------------------------------

CREATE TABLE RoljRazr (
  rolj VARCHAR(50) NOT NULL,
  razr VARCHAR(50) NOT NULL,
  CONSTRAINT RoljRazr_rolj_razr PRIMARY KEY(rolj, razr)
);

CREATE INDEX RoljRazr_rolj ON RoljRazr(rolj);

CREATE INDEX RoljRazr_razr ON RoljRazr(razr);

-- ALTER TABLE RoljRazr DROP CONSTRAINT IF EXISTS rr_rolj;

ALTER TABLE RoljRazr ADD CONSTRAINT rr_rolj FOREIGN KEY (rolj) REFERENCES security.Rolj(rolj) ON UPDATE CASCADE ON DELETE CASCADE;

-- ALTER TABLE RoljRazr DROP CONSTRAINT IF EXISTS rr_razr;

ALTER TABLE RoljRazr ADD CONSTRAINT rr_razr FOREIGN KEY (razr) REFERENCES security.Razr(razr) ON UPDATE CASCADE ON DELETE CASCADE;

------------------------------------------------------------------------

CREATE TABLE SidRolj (
  sid VARCHAR(50) NOT NULL,
  rolj VARCHAR(50) NOT NULL,
  CONSTRAINT SidRolj_sid_rolj PRIMARY KEY(sid, rolj)
);

CREATE INDEX SidRolj_sid ON SidRolj(sid);

CREATE INDEX SidRolj_rolj ON SidRolj(rolj);

-- ALTER TABLE SidRolj DROP CONSTRAINT IF EXISTS sr_rolj;

ALTER TABLE SidRolj ADD CONSTRAINT sr_rolj FOREIGN KEY (rolj) REFERENCES security.Rolj(rolj) ON UPDATE CASCADE ON DELETE CASCADE;

-- ALTER TABLE SidRolj DROP CONSTRAINT IF EXISTS sr_sid;

ALTER TABLE SidRolj ADD CONSTRAINT sr_sid FOREIGN KEY (sid) REFERENCES security.Poljzovatelj(sid) ON UPDATE CASCADE ON DELETE CASCADE;
