CREATE OR REPLACE FUNCTION update_total_participants_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the total_participants count for the contest in the Contest table
    UPDATE contests
    SET total_participants = (
        SELECT COUNT(*) 
        FROM contest_contest_participant_join_table 
        WHERE contest_id = OLD.contest_id
    )
    WHERE contest_id = OLD.contest_id;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER update_participant_count_on_delete
AFTER DELETE ON contest_contest_participant_join_table
FOR EACH ROW
EXECUTE FUNCTION update_total_participants_on_delete();
