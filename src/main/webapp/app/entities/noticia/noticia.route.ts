import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { NoticiaComponent } from './noticia.component';
import { NoticiaDetailComponent } from './noticia-detail.component';
import { NoticiaPopupComponent } from './noticia-dialog.component';
import { NoticiaDeletePopupComponent } from './noticia-delete-dialog.component';

@Injectable()
export class NoticiaResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const noticiaRoute: Routes = [
    {
        path: 'noticia',
        component: NoticiaComponent,
        resolve: {
            'pagingParams': NoticiaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Noticias'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'noticia/:id',
        component: NoticiaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Noticias'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const noticiaPopupRoute: Routes = [
    {
        path: 'noticia-new',
        component: NoticiaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Noticias'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticia/:id/edit',
        component: NoticiaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Noticias'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'noticia/:id/delete',
        component: NoticiaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Noticias'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
