-- V1__create_partition_function.sql
CREATE OR REPLACE FUNCTION create_partition_if_not_exists(p_date DATE) RETURNS void AS $function$
DECLARE
    partition_name TEXT;
    start_date DATE;
    end_date DATE;
BEGIN
    partition_name := 'contests_' || to_char(p_date, 'YYYY_MM');
    start_date := date_trunc('month', p_date);
    end_date := start_date + INTERVAL '1 month';

    IF NOT EXISTS (SELECT FROM pg_tables WHERE tablename = partition_name) THEN
        EXECUTE format('CREATE TABLE %I PARTITION OF contests FOR VALUES FROM (%L) TO (%L);',
                        partition_name, start_date, end_date);
    END IF;
END;
$function$ LANGUAGE plpgsql;

----------------------------the below trigger is for calling the above function before any data entry ------------

CREATE OR REPLACE FUNCTION handle_partition_insert() RETURNS TRIGGER AS $function$
BEGIN
    PERFORM create_partition_if_not_exists(NEW.contest_date);
    RETURN NEW;
END;
$function$ LANGUAGE plpgsql;

--- below logic is for attaching the trigger to table (main)   -------------------------------

CREATE TRIGGER trigger_partition_insert
BEFORE INSERT ON orders
FOR EACH ROW EXECUTE FUNCTION handle_partition_insert();




CREATE TABLE contest_problems_easy PARTITION OF contest_problems
    FOR VALUES IN ('easy');

CREATE TABLE contest_problems_medium PARTITION OF contest_problems
    FOR VALUES IN ('medium');

CREATE TABLE contest_problems_hard PARTITION OF contest_problems
    FOR VALUES IN ('hard');

-- creating composite indexes in contest_results on id and rank , becuase they we will be queried more often.
-- and even if we just want to query on single column still useful
 
CREATE INDEX idx_rank_in_this_contest_with_sh_id
ON contest_results (rank_in_this_contest , hashtext(sh_id));

--CREATE INDEX idx_email ON t_email(hashtext(email));

CREATE INDEX idx_batch_branch ON Student(batch , branch);

CREATE INDEX idx_gsuite_email on Student(hashtext(gsuite_email));



CREATE OR REPLACE FUNCTION create_partition_if_not_exists(p_date DATE) RETURNS void AS $function$
DECLARE
    partition_name TEXT;
    start_date DATE;
    end_date DATE;
BEGIN
    partition_name := 'contests_' || to_char(p_date, 'YYYY_MM');
    start_date := date_trunc('month', p_date);
    end_date := start_date + INTERVAL '1 month';

    IF NOT EXISTS (SELECT FROM pg_tables WHERE tablename = partition_name) THEN
        -- Lock the parent table before creating the partition
        LOCK TABLE contests IN EXCLUSIVE MODE;

        EXECUTE format('CREATE TABLE %I PARTITION OF contests FOR VALUES FROM (%L) TO (%L);',
                        partition_name, start_date, end_date);
    END IF;
END;
$function$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION handle_partition_insert() RETURNS TRIGGER AS $function$
BEGIN
    PERFORM create_partition_if_not_exists(NEW.contest_date::DATE);
    RETURN NEW;
END;
$function$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_partition_insert
BEFORE INSERT ON contests
FOR EACH ROW EXECUTE FUNCTION handle_partition_insert();


