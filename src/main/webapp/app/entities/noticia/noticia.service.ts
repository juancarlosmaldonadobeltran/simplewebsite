import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { Noticia } from './noticia.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class NoticiaService {

    private resourceUrl = 'api/noticias';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(noticia: Noticia): Observable<Noticia> {
        const copy = this.convert(noticia);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(noticia: Noticia): Observable<Noticia> {
        const copy = this.convert(noticia);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: string): Observable<Noticia> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.fecha = this.dateUtils
            .convertLocalDateFromServer(entity.fecha);
    }

    private convert(noticia: Noticia): Noticia {
        const copy: Noticia = Object.assign({}, noticia);
        copy.fecha = this.dateUtils
            .convertLocalDateToServer(noticia.fecha);
        return copy;
    }
}
