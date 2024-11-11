export class Projeto{

    id?:   String;  
    nome?: String;
    image?: String;
    
        constructor(id: string, nome: string, image: string){
            this.id = id;
            this.nome = nome;
            this.image = image;
        }
    
    }