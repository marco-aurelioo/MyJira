import { Tasks } from "./Tasks";

export class Profile{

userId?:  string;  
name?:    string;
avatar?:  string;
characteristics?: string[];
tasks?: Tasks[];

    constructor(userId: string, name: string, avatar: string){
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }


}