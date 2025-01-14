from flask import Flask, request, jsonify
from joblib import load 
import pandas as pd
import numpy as np

app = Flask(__name__)


model_profile = load('model/profile/profile_model.pkl')
model_top500 = load('model/top500/top500_model.pkl')
model_profit = load('model/profit/profit_model.pkl')

def ProfileModel(new_request_data):
  columns_to_encode = ['Education', 'Projet_Individuel', 'Genre', 'EnVille', 
                     'Influence', 'TroubleMental', 'TraitsCles']
  for column in columns_to_encode:
    encoder = load(f"model/profile/{column}.pkl")
    new_request_data[column] = encoder.transform(new_request_data[column])
  prediction = model_profile.predict(new_request_data)
  return prediction


def Top500Model(new_request_data):
  columns_to_encode = ['Ville', 'Categorie']

  for column in columns_to_encode:
    encoder = load(f"model/top500/{column}.pkl")
    new_request_data[column] = encoder.transform(new_request_data[column])
  prediction = model_top500.predict(new_request_data)
  return prediction


def ProfitModel(new_request_data):
  encoder = load(f"model/profit/Region.pkl")
  new_request_data['Region'] = encoder.transform(new_request_data['Region'])
  prediction = model_profit.predict(new_request_data)
  return prediction


@app.route('/profile', methods=['GET'])
def get_profile():
  colonnes = load('model/profile/profile_colonnes.pkl')
  valeurs_trait= load('model/profile/traits_valeurs.pkl')
  valeurs_education = load('model/profile/education_valeurs.pkl')
  return jsonify({
    "colonnes": list(colonnes),
    "valeurs_trait": list(valeurs_trait),
    "valeurs_education": list(valeurs_education)
  })

@app.route('/profile', methods=['POST'])
def predict_profile():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    "Education": request_data['Education'],
    "Projet_Individuel": request_data['Projet_Individuel'], 
    "Age": request_data['Age'], 
    "Genre": request_data['Genre'],
    "EnVille": request_data['EnVille'], 
    "Influence": request_data['Influence'], 
    "DegPerseverance": request_data['DegPerseverance'], 
    "DegInitiative": request_data['DegInitiative'],
    "DegCompetition": request_data['DegCompetition'], 
    "DegAutonomie": request_data['DegAutonomie'], 
    "DegBesoinReussite": request_data['DegBesoinReussite'],
    "DegConfiance": request_data['DegConfiance'], 
    "DegSante": request_data['DegSante'], 
    "TroubleMental": request_data['TroubleMental'], 
    "TraitsCles": request_data['TraitsCles']
}])

  
  prediction = ProfileModel(new_request_data)
  if(prediction == 0):
    prediction = "Vous n'avez pas le profil d'un entrepreneur"
  else:
    prediction = "Vous avez le profil d'un entrepreneur"
  return jsonify(prediction)

@app.route('/top500', methods=['GET'])
def get_top500():
  colonnes = load('model/top500/top500_colonnes.pkl')
  valeurs_ville = load('model/top500/ville_valeurs.pkl') 
  valeurs_categorie = load('model/top500/categorie_valeurs.pkl')
  return jsonify({
    "colonnes": list(colonnes),
    "valeurs_ville": list(valeurs_ville),
    "valeurs_categorie": list(valeurs_categorie)
  })


@app.route('/top500', methods=['POST'])
def predict_top500():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    'Ville': request_data['Ville'],
        'Nombre_relations': request_data['Nombre_relations'],
        'Tours_financement': request_data['Tours_financement'],
        'Capitale_fonds': request_data['Capitale_fonds'],
        'Categorie': request_data['Categorie'],
        'Investisseurs_providentiels': request_data['Investisseurs_providentiels'],
        'Nombre_participants': request_data['Nombre_participants'],

    }])
  prediction = Top500Model(new_request_data)
  if(prediction == 1):
    prediction = "Votre startup peut devenir un des top 500"
  else:
    prediction = "Votre startup nécessite des améliorations pour devenir un des top 500"
  return jsonify(prediction)

@app.route('/profit', methods=['GET'])
def get_profit():
  colonnes = load('model/profit/profit_colonnes.pkl')
  valeurs_region = load('model/profit/region_valeurs.pkl')
  return jsonify({
    "colonnes": list(colonnes),
    "valeurs_region": list(valeurs_region)
  })

@app.route('/profit', methods=['POST'])
def predict_profit():
  request_data = request.get_json()
  new_request_data = pd.DataFrame([{
    'R&D': request_data['R&D'],
    'Administration': request_data['Administration'],
    'Marketing': request_data['Marketing'],
    'Region': request_data['Region'],
  }])
  prediction = ProfitModel(new_request_data)
  prediction = str(prediction)
  return jsonify(prediction[2:-2])


if __name__ == '__main__':
    app.run(debug=True)
