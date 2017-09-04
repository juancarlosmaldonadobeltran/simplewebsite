import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimplewebsiteSharedModule } from '../../shared';
import {
    NoticiaService,
    NoticiaPopupService,
    NoticiaComponent,
    NoticiaDetailComponent,
    NoticiaDialogComponent,
    NoticiaPopupComponent,
    NoticiaDeletePopupComponent,
    NoticiaDeleteDialogComponent,
    noticiaRoute,
    noticiaPopupRoute,
    NoticiaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...noticiaRoute,
    ...noticiaPopupRoute,
];

@NgModule({
    imports: [
        SimplewebsiteSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        NoticiaComponent,
        NoticiaDetailComponent,
        NoticiaDialogComponent,
        NoticiaDeleteDialogComponent,
        NoticiaPopupComponent,
        NoticiaDeletePopupComponent,
    ],
    entryComponents: [
        NoticiaComponent,
        NoticiaDialogComponent,
        NoticiaPopupComponent,
        NoticiaDeleteDialogComponent,
        NoticiaDeletePopupComponent,
    ],
    providers: [
        NoticiaService,
        NoticiaPopupService,
        NoticiaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SimplewebsiteNoticiaModule {}
