import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Noticia } from './noticia.model';
import { NoticiaPopupService } from './noticia-popup.service';
import { NoticiaService } from './noticia.service';

@Component({
    selector: 'jhi-noticia-dialog',
    templateUrl: './noticia-dialog.component.html'
})
export class NoticiaDialogComponent implements OnInit {

    noticia: Noticia;
    isSaving: boolean;
    fechaDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private noticiaService: NoticiaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.noticia.id !== undefined) {
            this.subscribeToSaveResponse(
                this.noticiaService.update(this.noticia));
        } else {
            this.subscribeToSaveResponse(
                this.noticiaService.create(this.noticia));
        }
    }

    private subscribeToSaveResponse(result: Observable<Noticia>) {
        result.subscribe((res: Noticia) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Noticia) {
        this.eventManager.broadcast({ name: 'noticiaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-noticia-popup',
    template: ''
})
export class NoticiaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private noticiaPopupService: NoticiaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.noticiaPopupService
                    .open(NoticiaDialogComponent as Component, params['id']);
            } else {
                this.noticiaPopupService
                    .open(NoticiaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
