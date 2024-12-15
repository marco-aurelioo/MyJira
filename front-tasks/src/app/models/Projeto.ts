export class Projeto{

    id?:   string;  
    nome?: string;
    image?: string;
    alias?: string;
    description?: string;

    
        constructor(id: string, nome: string, image: string){
            this.id = id;
            this.nome = nome;
            this.image = image;
        }
    
    }