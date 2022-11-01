package com.prj.lms.domain.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartItemEntity is a Querydsl query type for CartItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartItemEntity extends EntityPathBase<CartItemEntity> {

    private static final long serialVersionUID = -1774704503L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartItemEntity cartItemEntity = new QCartItemEntity("cartItemEntity");

    public final com.prj.lms.domain.audit.QBaseEntity _super = new com.prj.lms.domain.audit.QBaseEntity(this);

    public final QCartEntity cartEntity;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.prj.lms.domain.item.QItemEntity itemEntity;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QCartItemEntity(String variable) {
        this(CartItemEntity.class, forVariable(variable), INITS);
    }

    public QCartItemEntity(Path<? extends CartItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartItemEntity(PathMetadata metadata, PathInits inits) {
        this(CartItemEntity.class, metadata, inits);
    }

    public QCartItemEntity(Class<? extends CartItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cartEntity = inits.isInitialized("cartEntity") ? new QCartEntity(forProperty("cartEntity"), inits.get("cartEntity")) : null;
        this.itemEntity = inits.isInitialized("itemEntity") ? new com.prj.lms.domain.item.QItemEntity(forProperty("itemEntity")) : null;
    }

}

