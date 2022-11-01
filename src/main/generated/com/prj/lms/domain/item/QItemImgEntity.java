package com.prj.lms.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemImgEntity is a Querydsl query type for ItemImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemImgEntity extends EntityPathBase<ItemImgEntity> {

    private static final long serialVersionUID = -134075501L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemImgEntity itemImgEntity = new QItemImgEntity("itemImgEntity");

    public final com.prj.lms.domain.audit.QBaseEntity _super = new com.prj.lms.domain.audit.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    public final QItemEntity itemEntity;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath repimgYn = createString("repimgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QItemImgEntity(String variable) {
        this(ItemImgEntity.class, forVariable(variable), INITS);
    }

    public QItemImgEntity(Path<? extends ItemImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemImgEntity(PathMetadata metadata, PathInits inits) {
        this(ItemImgEntity.class, metadata, inits);
    }

    public QItemImgEntity(Class<? extends ItemImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemEntity = inits.isInitialized("itemEntity") ? new QItemEntity(forProperty("itemEntity")) : null;
    }

}

