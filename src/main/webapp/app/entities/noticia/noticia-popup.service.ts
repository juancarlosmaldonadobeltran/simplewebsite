import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Noticia } from './noticia.model';
import { NoticiaService } from './noticia.service';

@Injectable()
export class NoticiaPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private noticiaService: NoticiaService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.noticiaService.find(id).subscribe((noticia) => {
                    if (noticia.fecha) {
                        noticia.fecha = {
                            year: noticia.fecha.getFullYear(),
                            month: noticia.fecha.getMonth() + 1,
                            day: noticia.fecha.getDate()
                        };
                    }
                    this.ngbModalRef = this.noticiaModalRef(component, noticia);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.noticiaModalRef(component, new Noticia());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    noticiaModalRef(component: Component, noticia: Noticia): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.noticia = noticia;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
