import { Pessoa } from "./Pessoa";

export class PagePessoa {
    last: boolean = false;
    totalElements: number=0;
    totalPages: number=0;
    size: number=0;
    number: number=0;
    first: boolean= false;
    numberOfElements: number =0;
    empty: boolean = false;
    content: Pessoa[] = [];
}