<template>
    <div class="manga-list container mt-4">
      <h2 v-if="category">Mangás da categoria: {{ category }}</h2>
      <div v-if="loading" class="text-center">Carregando...</div>
      <div v-if="error" class="text-danger">Erro: {{ error }}</div>
      <div v-if="mangas.length === 0 && !loading" class="alert alert-warning">
        Nenhum mangá encontrado para esta categoria.
      </div>
      
      <div v-else class="row">
        <MangaCard
          v-for="manga in mangas"
          :key="manga.id"
          :id="manga.id"
          :cover="manga.cover"
          :title="manga.title"
          :number="manga.number"
          :price="manga.price"
          class="col-lg-3 col-md-4 col-sm-6 mb-4"
        />
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted, watch } from 'vue';
  import { useRoute } from 'vue-router';
  import axios from 'axios';
  import MangaCard from '../components/MangaCard.vue';
  
  const mangas = ref<any[]>([]);
  const loading = ref(false);
  const error = ref<string | null>(null);
  
  const route = useRoute();
  const category = ref(route.params.category as string);
  
  const fetchMangasByCategory = async () => {
    loading.value = true;
    error.value = null;
    mangas.value = [];
    try {
      const response = await axios.get('http://localhost:8080/mangas/filterByCategory', {
        params: { category: category.value }
      });
      mangas.value = response.data;
    } catch (err) {
      error.value = 'Erro ao buscar mangás';
      console.error(err);
    } finally {
      loading.value = false;
    }
  };
  
  watch(() => route.params.category, (newCategory) => {
    category.value = newCategory as string;
    fetchMangasByCategory();
  });
  
  onMounted(() => {
    fetchMangasByCategory();
  });
  </script>
  
  <style scoped>
  .manga-list {
    margin-top: 20px;
  }
  </style>
  