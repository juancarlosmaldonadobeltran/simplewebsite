/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SimplewebsiteTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { NoticiaDetailComponent } from '../../../../../../main/webapp/app/entities/noticia/noticia-detail.component';
import { NoticiaService } from '../../../../../../main/webapp/app/entities/noticia/noticia.service';
import { Noticia } from '../../../../../../main/webapp/app/entities/noticia/noticia.model';

describe('Component Tests', () => {

    describe('Noticia Management Detail Component', () => {
        let comp: NoticiaDetailComponent;
        let fixture: ComponentFixture<NoticiaDetailComponent>;
        let service: NoticiaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SimplewebsiteTestModule],
                declarations: [NoticiaDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    NoticiaService,
                    JhiEventManager
                ]
            }).overrideTemplate(NoticiaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NoticiaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NoticiaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Noticia('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.noticia).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
