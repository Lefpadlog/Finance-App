package com.lefpadlog.financeapp.code.data.payment;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PaymentDao_Impl implements PaymentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Payment> __insertionAdapterOfPayment;

  private final EntityDeletionOrUpdateAdapter<Payment> __deletionAdapterOfPayment;

  private final EntityDeletionOrUpdateAdapter<Payment> __updateAdapterOfPayment;

  public PaymentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPayment = new EntityInsertionAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `payment_table` (`id`,`title`,`description`,`date`,`type`,`from`,`to`,`amount`,`interval`,`originalId`,`lastIntervalDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Payment entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getDate());
        statement.bindString(5, entity.getType());
        statement.bindString(6, entity.getFrom());
        statement.bindString(7, entity.getTo());
        statement.bindDouble(8, entity.getAmount());
        statement.bindString(9, entity.getInterval());
        if (entity.getOriginalId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getOriginalId());
        }
        if (entity.getLastIntervalDate() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getLastIntervalDate());
        }
      }
    };
    this.__deletionAdapterOfPayment = new EntityDeletionOrUpdateAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `payment_table` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Payment entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPayment = new EntityDeletionOrUpdateAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `payment_table` SET `id` = ?,`title` = ?,`description` = ?,`date` = ?,`type` = ?,`from` = ?,`to` = ?,`amount` = ?,`interval` = ?,`originalId` = ?,`lastIntervalDate` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Payment entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getDate());
        statement.bindString(5, entity.getType());
        statement.bindString(6, entity.getFrom());
        statement.bindString(7, entity.getTo());
        statement.bindDouble(8, entity.getAmount());
        statement.bindString(9, entity.getInterval());
        if (entity.getOriginalId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getOriginalId());
        }
        if (entity.getLastIntervalDate() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getLastIntervalDate());
        }
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public Object addPayment(final Payment payment, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPayment.insert(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removePayment(final Payment payment, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPayment.handle(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePayment(final Payment payment, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPayment.handle(payment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Payment>> getAllPayments() {
    final String _sql = "SELECT * FROM payment_table ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"payment_table"}, false, new Callable<List<Payment>>() {
      @Override
      @Nullable
      public List<Payment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
          final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOriginalId = CursorUtil.getColumnIndexOrThrow(_cursor, "originalId");
          final int _cursorIndexOfLastIntervalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastIntervalDate");
          final List<Payment> _result = new ArrayList<Payment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Payment _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpFrom;
            _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
            final String _tmpTo;
            _tmpTo = _cursor.getString(_cursorIndexOfTo);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final String _tmpInterval;
            _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            final Integer _tmpOriginalId;
            if (_cursor.isNull(_cursorIndexOfOriginalId)) {
              _tmpOriginalId = null;
            } else {
              _tmpOriginalId = _cursor.getInt(_cursorIndexOfOriginalId);
            }
            final String _tmpLastIntervalDate;
            if (_cursor.isNull(_cursorIndexOfLastIntervalDate)) {
              _tmpLastIntervalDate = null;
            } else {
              _tmpLastIntervalDate = _cursor.getString(_cursorIndexOfLastIntervalDate);
            }
            _item = new Payment(_tmpId,_tmpTitle,_tmpDescription,_tmpDate,_tmpType,_tmpFrom,_tmpTo,_tmpAmount,_tmpInterval,_tmpOriginalId,_tmpLastIntervalDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Payment>> getRepeatedPayments() {
    final String _sql = "SELECT * FROM payment_table WHERE interval!='Once' AND originalId IS NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"payment_table"}, false, new Callable<List<Payment>>() {
      @Override
      @Nullable
      public List<Payment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
          final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOriginalId = CursorUtil.getColumnIndexOrThrow(_cursor, "originalId");
          final int _cursorIndexOfLastIntervalDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastIntervalDate");
          final List<Payment> _result = new ArrayList<Payment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Payment _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpFrom;
            _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
            final String _tmpTo;
            _tmpTo = _cursor.getString(_cursorIndexOfTo);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final String _tmpInterval;
            _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            final Integer _tmpOriginalId;
            if (_cursor.isNull(_cursorIndexOfOriginalId)) {
              _tmpOriginalId = null;
            } else {
              _tmpOriginalId = _cursor.getInt(_cursorIndexOfOriginalId);
            }
            final String _tmpLastIntervalDate;
            if (_cursor.isNull(_cursorIndexOfLastIntervalDate)) {
              _tmpLastIntervalDate = null;
            } else {
              _tmpLastIntervalDate = _cursor.getString(_cursorIndexOfLastIntervalDate);
            }
            _item = new Payment(_tmpId,_tmpTitle,_tmpDescription,_tmpDate,_tmpType,_tmpFrom,_tmpTo,_tmpAmount,_tmpInterval,_tmpOriginalId,_tmpLastIntervalDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
