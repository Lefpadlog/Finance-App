package com.lefpadlog.financeapp.code.data.paymentmethod;

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
public final class PaymentMethodDao_Impl implements PaymentMethodDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PaymentMethod> __insertionAdapterOfPaymentMethod;

  private final EntityDeletionOrUpdateAdapter<PaymentMethod> __deletionAdapterOfPaymentMethod;

  private final EntityDeletionOrUpdateAdapter<PaymentMethod> __updateAdapterOfPaymentMethod;

  public PaymentMethodDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPaymentMethod = new EntityInsertionAdapter<PaymentMethod>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `payment_method_table` (`title`,`description`,`information`,`type`,`amount`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentMethod entity) {
        statement.bindString(1, entity.getTitle());
        statement.bindString(2, entity.getDescription());
        statement.bindString(3, entity.getInformation());
        statement.bindString(4, entity.getType());
        statement.bindDouble(5, entity.getAmount());
      }
    };
    this.__deletionAdapterOfPaymentMethod = new EntityDeletionOrUpdateAdapter<PaymentMethod>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `payment_method_table` WHERE `title` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentMethod entity) {
        statement.bindString(1, entity.getTitle());
      }
    };
    this.__updateAdapterOfPaymentMethod = new EntityDeletionOrUpdateAdapter<PaymentMethod>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `payment_method_table` SET `title` = ?,`description` = ?,`information` = ?,`type` = ?,`amount` = ? WHERE `title` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentMethod entity) {
        statement.bindString(1, entity.getTitle());
        statement.bindString(2, entity.getDescription());
        statement.bindString(3, entity.getInformation());
        statement.bindString(4, entity.getType());
        statement.bindDouble(5, entity.getAmount());
        statement.bindString(6, entity.getTitle());
      }
    };
  }

  @Override
  public Object addPaymentMethod(final PaymentMethod paymentMethod,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaymentMethod.insert(paymentMethod);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removePaymentMethod(final PaymentMethod paymentMethod,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPaymentMethod.handle(paymentMethod);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePaymentMethod(final PaymentMethod paymentMethod,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPaymentMethod.handle(paymentMethod);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<PaymentMethod>> getAllPaymentMethods() {
    final String _sql = "SELECT * FROM payment_method_table ORDER BY title ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"payment_method_table"}, false, new Callable<List<PaymentMethod>>() {
      @Override
      @Nullable
      public List<PaymentMethod> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfInformation = CursorUtil.getColumnIndexOrThrow(_cursor, "information");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final List<PaymentMethod> _result = new ArrayList<PaymentMethod>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentMethod _item;
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpInformation;
            _tmpInformation = _cursor.getString(_cursorIndexOfInformation);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            _item = new PaymentMethod(_tmpTitle,_tmpDescription,_tmpInformation,_tmpType,_tmpAmount);
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
