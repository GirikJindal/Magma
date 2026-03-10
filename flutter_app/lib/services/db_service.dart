import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class DbService {
  DbService._();
  static final DbService instance = DbService._();

  Database? _db;

  Future<void> init() async {
    final path = await getDatabasesPath();
    final dbPath = join(path, 'magma.db');
    _db = await openDatabase(dbPath, version: 1, onCreate: (db, v) async {
      await db.execute('''
        CREATE TABLE points (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          userId TEXT,
          points INTEGER
        )
      ''');
      await db.execute('''
        CREATE TABLE tasks (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          title TEXT,
          description TEXT,
          assignedTo TEXT,
          completed INTEGER DEFAULT 0
        )
      ''');
    });
  }

  Future<int> addPoints(String userId, int points) async {
    return await _db!.insert('points', {'userId': userId, 'points': points});
  }

  Future<List<Map<String, Object?>>> getTasks() async {
    return await _db!.query('tasks');
  }
}
