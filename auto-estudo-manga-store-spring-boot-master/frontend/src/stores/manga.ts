import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { api } from '../baseConfig'
import { useUserStore } from '../stores/user'
import { ApplicationError, getAppError } from '../mixin/errorMessageMixing'
import { authenticationHeader } from '../mixin/authenticationMixing'

interface Comments {
    id: number,
    description: string,
    rating: number
}

export interface Manga {
    id: number,
    title: string,
    number: number,
    price: number,
    cover: string,
    summary:string,
    comments: Comments[]
    
}

interface Pagination {
    totalElements: number,
    size: number,
    totalPages: number,
    number: number

}

export type MangaCollection = {
    mangas: Manga[],
    pagination: Pagination
}

export const useMangaStore = defineStore('manga', () => {
    const mangas = ref<Manga[]>([])
    const pagination = ref<Pagination>({
        totalElements: 0,
        size: 0,
        totalPages: 0,
        number: 0
    })

    async function all(page: number = 0): Promise<MangaCollection | ApplicationError> {
        try {
            const { data } = await api.get('/mangas', {
                params: {
                    "size": 24,
                    "page": page,
                    "sort": "id"
                }
            })
            return { mangas: data._embedded.mangas,  pagination: data.page}
        } catch(error) {
            return getAppError(error)
        }
    }
        
    async function get(id: number): Promise<Manga | ApplicationError> {
        try {
            const { data } = await api.get(`/mangas/${id}?projection=customManga`, {
               
            })
            return data
        } catch(error) {
            return getAppError(error)
        }
    }

    async function create(manga: FormData): Promise<Manga | ApplicationError> {
        try {
            const store = useUserStore()
            const { data } = await api.post(`/mangas/save`, manga, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            return data
        } catch(error) {
            return getAppError(error)
            
        }
    }
        
    async function remove(id: number): Promise<Manga | ApplicationError> {
        try {
            const store = useUserStore()
            console.log(store.token)
            const { data } = await api.delete(`/mangas/${id}`, {
                headers: authenticationHeader(store.token)
            })
            const mangaDeleted = mangas.value.find( manga => manga.id === id)
            if (mangaDeleted) {
                mangas.value.splice(mangas.value.indexOf(mangaDeleted), 1)
            }
            return data
        } catch(error) {
           return getAppError(error)
        }
    }
    
    async function update(manga: Manga, newCover?: FormData): Promise<Manga | ApplicationError> {
        const { id } = manga
        try {
            const store = useUserStore()
            const { data } = await api.put(`/mangas/update/${id}`, newCover, {
                headers: {
                    'Content-Type': 'multipart/form-data'
            
                }
            })
            return get(id)
        } catch(error) {
            return getAppError(error)
        }
    }
    
    return { mangas, pagination, all, create, get, remove, update }
})