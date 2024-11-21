CREATE OR REPLACE FUNCTION update_total_participants()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the total_participants count for the contest in the Contest table
    UPDATE contests
    SET total_participants = (
        SELECT COUNT(*) 
        FROM contest_contest_participant_join_table 
        WHERE contest_id = NEW.contest_id
    )
    WHERE contest_id = NEW.contest_id;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_participant_count
AFTER INSERT ON contest_contest_participant_join_table
FOR EACH ROW
EXECUTE FUNCTION update_total_participants();
