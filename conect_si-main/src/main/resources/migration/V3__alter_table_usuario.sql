DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'usuario'
          AND column_name = 'id'
          AND is_identity = 'YES'
    ) THEN
        EXECUTE 'ALTER TABLE usuario ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY';
    END IF;
END$$;
