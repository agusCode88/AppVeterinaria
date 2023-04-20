package bootcamp.cl.ejemplo.appveterinarioperruno.basedatos;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration1To2 extends Migration {
    public Migration1To2() {
        super(1, 2);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {


        // Aquí se deben agregar las sentencias SQL para actualizar la base de datos
    }
}

