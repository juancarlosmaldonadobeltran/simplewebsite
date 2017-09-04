import { BaseEntity } from './../../shared';

export class Noticia implements BaseEntity {
    constructor(
        public id?: string,
        public fecha?: any,
        public titulo?: string,
        public contenido?: string,
    ) {
    }
}
