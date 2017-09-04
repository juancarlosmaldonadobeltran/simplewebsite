import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Noticia } from './noticia.model';
import { NoticiaPopupService } from './noticia-popup.service';
import { NoticiaService } from './noticia.service';

@Component({
    selector: 'jhi-noticia-delete-dialog',
    templateUrl: './noticia-delete-dialog.component.html'
})
export class NoticiaDeleteDialogComponent {

    noticia: Noticia;

    constructor(
        private noticiaService: NoticiaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.noticiaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'noticiaListModification',
                content: 'Deleted an noticia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-noticia-delete-popup',
    template: ''
})
export class NoticiaDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiaPopupService: NoticiaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.noticiaPopupService
                .open(NoticiaDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
