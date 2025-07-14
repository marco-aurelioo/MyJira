import { Tarefa } from "./Tarefa";


export class Profile{

userId?:  string;  
name?:    string;
avatar?:  string;
characteristics?: string[];
tasks?: Tarefa[];

    constructor(userId: string, name: string, avatar: string){
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }


}