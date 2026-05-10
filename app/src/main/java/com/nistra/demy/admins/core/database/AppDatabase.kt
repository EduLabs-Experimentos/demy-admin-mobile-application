package com.nistra.demy.admins.core.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

/**
 * Main database for the Demy Admins application.
 *
 * This is the Room database configuration that holds all entities and provides
 * access to data access objects (DAOs).
 *
 * Note: The "Item" naming throughout this database is used as a placeholder/reference
 * and should be replaced with actual domain-specific entities as the application evolves.
 *
 * @author Salim Ramirez
 */
@Database(
    entities = [
        ItemEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides access to item-related database operations.
     *
     * @return The [ItemDao] instance for performing CRUD operations on items
     */
    abstract fun itemDao(): ItemDao
}

/**
 * Entity representing an item in the database.
 *
 * Note: "Item" is used as a placeholder name. Replace this with your actual
 * domain-specific entity (e.g., Product, User, Course, etc.) when implementing
 * real features.
 *
 * @property id Unique identifier for the item
 * @property title Display title or name of the item
 * @author Salim Ramirez
 */
@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val title: String
)

/**
 * Data Access Object for item operations.
 *
 * Provides methods to perform database operations on [ItemEntity].
 * Note: "Item" is a placeholder name and should be replaced with actual
 * domain entities as needed.
 *
 * @author Salim Ramirez
 */
@Dao
interface ItemDao {
    /**
     * Retrieves all items from the database.
     *
     * @return A list of all [ItemEntity] objects stored in the database
     */
    @Query("SELECT * FROM items")
    suspend fun getAll(): List<ItemEntity>

    /**
     * Inserts or updates a list of items in the database.
     *
     * If an item with the same ID already exists, it will be replaced with the new data.
     *
     * @param items The list of [ItemEntity] objects to insert or update
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ItemEntity>)
}
