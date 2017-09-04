import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Noticia } from './noticia.model';
import { NoticiaService } from './noticia.service';

@Component({
    selector: 'jhi-noticia-detail',
    templateUrl: './noticia-detail.component.html'
})
export class NoticiaDetailComponent implements OnInit, OnDestroy {

    noticia: Noticia;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private noticiaService: NoticiaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNoticias();
    }

    load(id) {
        this.noticiaService.find(id).subscribe((noticia) => {
            this.noticia = noticia;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNoticias() {
        this.eventSubscriber = this.eventManager.subscribe(
            'noticiaListModification',
            (response) => this.load(this.noticia.id)
        );
    }
}
