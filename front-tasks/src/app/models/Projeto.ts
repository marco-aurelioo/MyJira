export class Projeto{

    projectId?:   string;  
    name?: string;
    description?: string;
    isPublic?: boolean;
    unicName?: string;
    imagem?: string;
    qtdRequest?: number;
    qtdInvites?: number;

    
        constructor(projectId: string, name: string, description: string, isPublic: boolean){
            this.projectId = projectId;
            this.name = name;
            this.description = description;
            this.isPublic = isPublic
        }
    
    }